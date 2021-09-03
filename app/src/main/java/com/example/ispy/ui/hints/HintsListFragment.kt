package com.example.ispy.ui.hints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ispy.databinding.FragmentHintsListBinding
import com.example.ispy.domain.usecase.CameraResult
import com.example.ispy.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HintsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HintsListFragment : Fragment() {

    private val hintsViewModel: HintsListViewModel by viewModel()
    private var _binding: FragmentHintsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHintsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cameraFab.setOnClickListener { hintsViewModel.launchCameraFlow() }

        hintsViewModel.hintLiveData.observe(viewLifecycleOwner, { data ->
            if (binding.hintsList.adapter == null) {
                binding.hintsList.layoutManager = LinearLayoutManager(context)
                binding.hintsList.adapter = HintListAdapter(data)
            } else {
                (binding.hintsList.adapter as? HintListAdapter)?.updateList(data)
            }
        })

        hintsViewModel.cameraLiveData.observe(viewLifecycleOwner, { cameraResult ->
            when (cameraResult) {
                is CameraResult.Success -> {
                    val uri = cameraResult.uri
                    (requireActivity() as MainActivity).route(uri)
                }
                CameraResult.Fail -> {
                    Toast.makeText(context, "Failed to take Picture", Toast.LENGTH_SHORT).show()
                }
                CameraResult.NoPermissions -> {
                    Toast.makeText(context, "Failed to take Picture", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HintsListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
