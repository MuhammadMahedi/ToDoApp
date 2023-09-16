package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.adapters.TaskAdapter
import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskCmp
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.databinding.InputTaskDialogBinding
import com.example.todoapp.viewModel.DoneViewModel
import com.example.todoapp.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: TaskAdapter
    val viewModel:HomeViewModel by viewModels()
    val cmpViewModel:DoneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllTasks()

        viewModel.taskList.observe(viewLifecycleOwner){
            adapter=TaskAdapter(requireContext(),it)
            binding.todoRv.adapter=adapter
            markAsComplete(adapter)
        }

        binding.addBtn.setOnClickListener{
            showDialogForInputTask()
        }

        binding.completeTasks.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment)
        }
    }

    private fun showDialogForInputTask() {
        val builder = AlertDialog.Builder(requireActivity())

        val ib=InputTaskDialogBinding.inflate(layoutInflater)
        builder.setView(ib.root)

        builder.setPositiveButton("Save") { dialog, _ ->
            var savedText = ib.inputEditText.text.toString()
            Toast.makeText(requireContext(), "Task Added to the ToDo list", Toast.LENGTH_SHORT).show()

            //here we add the task to the db
            viewModel.addTask(Task(0,savedText))

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun markAsComplete(adapter: TaskAdapter){
        adapter.setOnClickListener(object:TaskAdapter.OnClickListener{
            override fun onDoneClick(task: Task) {
                //cmpViewModel.addTask(task)
                //viewModel.deleteTask(task)
                showAlertDialog(task)
            }

        })

    }

    private fun showAlertDialog(task:Task){
        val builder1 = AlertDialog.Builder(requireActivity())

        builder1.setTitle("Did you complete this task??")
            .setMessage("Once completed then it will be saved in the Complete Task's List")
            .setPositiveButton("Yes, Task Completed"){dialog,_->
                cmpViewModel.addTask(task)
                viewModel.deleteTask(task)
                Toast.makeText(requireContext(), "Task is stored in the Completed Tasks", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }.setNegativeButton("Its not done yet"){dialog,_->
                dialog.cancel()
            }.show()
    }

}