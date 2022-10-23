package es.sublimestudio.viewmodelwithapirest.views.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import es.sublimestudio.viewmodelwithapirest.R
import es.sublimestudio.viewmodelwithapirest.adapters.GenresAdapter
import kotlinx.coroutines.launch


class GenresFragment : Fragment() {
    private val viewModel: GenresViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: GenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressDialog)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // podemos usar varios "launch"
                launch {
                    viewModel.loading.collect { loading ->
                        if (loading) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }

                launch {
                    viewModel.genresList.collect {
                        adapter.updateData(it.genres)
                    }
                }

            }
        }

        adapter = GenresAdapter {
            // TODO navigate
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter

        viewModel.getGenres()
    }

}