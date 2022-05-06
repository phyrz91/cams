package com.vladpen.cams

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladpen.GroupData
import com.vladpen.GroupsAdapter
import com.vladpen.Navigator
import com.vladpen.cams.databinding.ActivityGroupsBinding

class GroupsActivity: AppCompatActivity() {
    private val binding by lazy { ActivityGroupsBinding.inflate(layoutInflater) }
    private val groups by lazy { GroupData.getGroups(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initActivity()
    }

    private fun initActivity() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = GroupsAdapter(groups)

        binding.toolbar.tvToolbarLabel.text = getString(R.string.groups)
        binding.toolbar.tvToolbarLink.text = getString(R.string.main_title)
        binding.toolbar.tvToolbarLink.setOnClickListener {
            back()
        }
        binding.toolbar.tvToolbarLink.setTextColor(getColor(R.color.live_link))
        binding.toolbar.btnBack.setImageResource(R.drawable.ic_baseline_menu_24)
        binding.toolbar.btnBack.setOnClickListener {
            MainMenu(this).showPopupMenu(it)
        }
        this.onBackPressedDispatcher.addCallback(callback)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            back()
        }
    }

    private fun back() {
        val intent = Intent(this, MainActivity::class.java)
        Navigator.go(this, intent)
    }
}
