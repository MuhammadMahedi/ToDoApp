package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.adapters.TaskCompleteAdapter
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.FragmentCompletedTaskBinding
import com.example.todoapp.viewModel.DoneViewModel
import com.example.todoapp.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

//AddTaskFragment
@AndroidEntryPoint
class CompletedTaskFragment : Fragment() {
    lateinit var binding: FragmentCompletedTaskBinding
    val cmpViewModel: DoneViewModel by viewModels()
    val hViewModel:HomeViewModel by viewModels()
    lateinit var adapter: TaskCompleteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCompletedTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This codes are for A toolbar with back button.
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.completedToolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.completedToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        cmpViewModel.getAllTasks()

        cmpViewModel.taskList.observe(viewLifecycleOwner){
            adapter= TaskCompleteAdapter(requireContext(),it)
            binding.doneRv.adapter=adapter
            undoTask(adapter)
        }

    }

    private fun undoTask(adapter: TaskCompleteAdapter){
        adapter.setOnClickListener(object:TaskCompleteAdapter.OnClickListener{
            override fun onDoneClick(task: Task) {
                //cmpViewModel.addTask(task)
                //viewModel.deleteTask(task)
                showAlertDialogUndo(task)
            }

            override fun onDeleteClick(model: Task) {
                showAlertDialogDelete(model)
            }

        })

    }

    private fun showAlertDialogUndo(task: Task){
        val builder1 = AlertDialog.Builder(requireActivity())

        builder1.setTitle("Is it unfinished somehow??")
            .setMessage("The unfinished tasks will be send back to the todo list")
            .setPositiveButton("Yes, Send it back"){dialog,_->
                hViewModel.addTask(task)
                cmpViewModel.deleteTask(task)
                dialog.dismiss()
                findNavController().popBackStack()

            }.setNegativeButton("It's completed"){dialog,_->
                dialog.cancel()
            }.show()
    }

    private fun showAlertDialogDelete(task: Task){
        val builder1 = AlertDialog.Builder(requireActivity())

        builder1.setTitle("Are You Sure??")
            .setMessage("This task will be deleted Permanently")
            .setPositiveButton("Delete"){dialog,_->
                cmpViewModel.deleteTask(task)
                dialog.dismiss()

            }.setNegativeButton("Cancel"){dialog,_->
                dialog.cancel()
            }.show()
    }



}