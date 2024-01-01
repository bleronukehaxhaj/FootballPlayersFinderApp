package com.footballplayersfinderapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.footballplayersfinderapp.R
import com.footballplayersfinderapp.adapter.PlayerAdapter
import com.footballplayersfinderapp.databinding.FragmentPlayersBinding
import com.footballplayersfinderapp.models.Player


class PlayersFragment : Fragment() {
    private lateinit var binding: FragmentPlayersBinding
    private val playerDetailsFragment = PlayerDetailsFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfPlayers = listOf(
            Player(R.drawable.messi, "Lionel", "Messi", 36),
            Player(R.drawable.mbappe, "Kylian", "Mbappé", 25),
            Player(R.drawable.haaland, "Erling", "Haaland", 23),
            Player(R.drawable.bruyne, "Kevin", "De Bruyne", 32),
            Player(R.drawable.benzema, "Karim", "Benzema", 36),
            Player(R.drawable.courtois, "Thibaut", "Courtois", 31),
            Player(R.drawable.kane, "Harry", "Kane", 30),
            Player(R.drawable.lewandowski, "Robert", "Lewandowski", 35),
            Player(R.drawable.salah, "Mohamed", "Salah", 31),
            Player(R.drawable.dias, "Rúben", "Dias", 27),
            Player(R.drawable.vinicius, "Vinícius", "Jr.", 23),
            Player(R.drawable.rodri, "Rodri", "Hernández", 27),
            Player(R.drawable.neymar, "Neymar", "Jr.", 31),
            Player(R.drawable.stegen, "Marc-André", "ter Stegen", 31),
            Player(R.drawable.dijk, "Virgil", "van Dijk", 32),
            Player(R.drawable.becker, "Alisson", "Becker", 30),
            Player(R.drawable.casimiro, "Carlos Henrique", "Casimiro", 31),
            Player(R.drawable.valverde, "Federico", "Valverde", 26),
            Player(R.drawable.osimhen, "Victor", "Osimhen", 25),
            Player(R.drawable.silva, "Bernardo", "Silva", 29),
            Player(R.drawable.kimmich, "Joshua", "Kimmich", 28),
            Player(R.drawable.fernandes, "Bruno Miguel Borges", "Fernandes", 29),
            Player(R.drawable.moraes, "Ederson Santana de", "Moraes", 30),
            Player(R.drawable.oblak, "Jan", "Oblak", 31),
            Player(R.drawable.griezmann, "Antoine", "Griezmann", 32),
            Player(R.drawable.ronaldo, "Cristiano", "Ronaldo", 38),
            Player(R.drawable.gea, "David", "De Gea", 32),
            Player(R.drawable.verratti, "Marco", "Verratti", 31),
            Player(R.drawable.martinez, "Lautaro", "Martínez", 26)
        )

        updatePlayerList(listOfPlayers)
        binding.buttonSearch.setOnClickListener {
            val input = binding.etSearch.text.toString()
            filterPlayers(input, listOfPlayers)
        }
        binding.lvPlayers.setOnItemClickListener { parent, view, position, id ->
            val selectedPlayer = binding.lvPlayers.adapter.getItem(position) as Player
            showPlayerDetails(selectedPlayer)
            setCurrentFragment(playerDetailsFragment)
        }
    }

    private fun filterPlayers(query: String, originalList: List<Player>) {
        val filteredPlayers = originalList.filter {
            it.firstName.contains(query, ignoreCase = true) ||
                    it.lastName.contains(query, ignoreCase = true)
        }

        if (filteredPlayers.isEmpty()) {
            Toast.makeText(requireContext(), "Player not found", Toast.LENGTH_SHORT).show()

            updatePlayerList(originalList)
        } else {
            updatePlayerList(filteredPlayers)
        }
    }


    private fun updatePlayerList(players: List<Player>) {
        val playerAdapter = PlayerAdapter(requireContext(), players)
        binding.lvPlayers.adapter = playerAdapter
    }

    private fun showPlayerDetails(player: Player) {
        playerDetailsFragment.arguments = Bundle().apply {
            putInt("photo", player.photo)
            putString("firstName", player.firstName)
            putString("lastName", player.lastName)
            putInt("age", player.age)
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            addToBackStack(null)
            commit()
        }
    }


}