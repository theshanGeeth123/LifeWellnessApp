package com.example.y2s2_assignment2.ui.habits.list

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.y2s2_assignment2.R
import com.example.y2s2_assignment2.databinding.FragmentHabitListBinding
import com.example.y2s2_assignment2.domain.repository.HabitRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HabitListFragment : Fragment() {

    private var _binding: FragmentHabitListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repo: HabitRepository
    private lateinit var adapter: HabitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo = HabitRepository(requireContext())

        adapter = HabitAdapter(
            items = repo.loadHabits(),
            isDone = { id -> repo.isDoneToday(id) },
            onToggle = { habit ->
                repo.toggleDoneToday(habit.id)
                updateProgress()
                adapter.notifyDataSetChanged()
            },
            onDelete = { habit ->
                repo.deleteHabit(habit.id)
                refreshList()
            }
        )

        binding.rvHabits.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHabits.adapter = adapter

        binding.fabAdd.setOnClickListener { showAddDialog() }

        refreshList()
    }

    private fun refreshList() {
        adapter.submitList(repo.loadHabits())
        updateProgress()
    }

    private fun updateProgress() {
        val p = repo.progressToday()
        binding.progressBar.progress = p.percent
        binding.tvProgress.text = getString(R.string.progress_format, p.done, p.total, p.percent)
    }

    private fun showAddDialog() {
        val input = EditText(requireContext()).apply {
            hint = "Habit name"
            inputType = InputType.TYPE_CLASS_TEXT
        }
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Habit")
            .setView(input)
            .setPositiveButton("Add") { d, _ ->
                val title = input.text.toString().trim()
                if (title.isNotEmpty()) {
                    repo.addHabit(title)
                    refreshList()
                }
                d.dismiss()
            }
            .setNegativeButton("Cancel") { d, _ -> d.dismiss() }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
