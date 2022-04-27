package com.example.mad_ind05_karjikar_abdulla

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_ind05_karjikar_abdulla.databinding.RecyclerviewRowBinding
import layout.StateDetails


class MyAdapter(val teamList: List<StateDetails>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        // Creating and initializing String objects which will be used to segue the details to DestinationActivity.
        private var stateFlag: String = ""
        private var stateMap: String = ""
        private var stateArea: String = ""


        // Defining the initializer which will be called along with constructor
        init {
            // This view holder is the parameter.
            binding.root.setOnClickListener(this)
        }

        // This function is used to bind the state details to the Text view on the MainActivity which has recyclerView.
        fun bindItem(team: StateDetails){
            binding.tvTeamName.text = team.stateName
            binding.subtitle.text = team.nickName

            // Setting the additional state details like State flag, State Map and area to the global variable in order to use them in OnClick() Method.
            stateFlag = team.stateImg
            stateMap = team.stateMap
            stateArea = team.area.getFormattedArea()
        }


        // Adding a function to Int class which will format the number in format XXX,XXX,XXX Sq. Miles
        fun Int.getFormattedArea(): String {
            val finalString = StringBuilder()
            val numLength = this.toString().length
            return if (numLength > 3) {
                for (i in numLength - 1 downTo 0) {
                    finalString.insert(0, this.toString()[i])
                    if ((i != numLength - 1) && i != 0 && (numLength - i) % 3 == 0)
                        finalString.insert(0, ",")
                }
                finalString.toString() + " Sq. Miles"
            } else
                this.toString() + " Sq. Miles"
        }

        // This function will be invoked when there is a tap on a cell of recycler view.
        override fun onClick(p0: View?) {

            // Setting the details to be passed to the Destination activity including state flag, state map, state area and state name
            p0?.let {
                val context = it.context
                val intent = Intent(context, DestinationActivity::class.java)
                    .putExtra("rowNum", adapterPosition.toString())
                    .putExtra("stateName", binding.tvTeamName.text)
                    .putExtra("stateFlag", stateFlag)
                    .putExtra("stateMap", stateMap)
                    .putExtra("stateArea", stateArea)
                context.startActivity(intent)
            }
        }
    }

    // This will create a new view holder whenever there aren't any existing view holders that has to be used by recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // This function will be called by recycler view to show the details.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val team = teamList[position]
        holder.bindItem(team)
    }

    // This will get the number of row to be displayed on the recycler view.
    override fun getItemCount(): Int {
        return teamList.size
    }
}