package com.example.ispy.ui.newhint

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ispy.databinding.FragmentNewHintBinding

class NewHintFragment : Fragment() {
    private var _binding: FragmentNewHintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewHintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val DATA = "DATA"
        fun newInstance(uri: Uri): NewHintFragment {
            val args = Bundle()
            args.putString(DATA, uri.toString())
            val fragment = NewHintFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
