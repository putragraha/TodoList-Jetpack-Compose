package nsystem.todojcompose.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import nsystem.todojcompose.R
import nsystem.todojcompose.databinding.FragmentTodoListBinding

class TodoListFragment: Fragment() {

    lateinit var binding: FragmentTodoListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        binding.btnNavigate.setOnClickListener {
            navController.navigate(R.id.action_todoListFragment_to_createTodoFragment)
        }
    }
}