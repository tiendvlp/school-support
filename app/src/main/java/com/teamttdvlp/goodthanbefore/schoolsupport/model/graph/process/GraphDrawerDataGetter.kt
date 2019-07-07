package com.teamttdvlp.goodthanbefore.schoolsupport.model.graph.process

import android.app.Activity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SpecialGraphMenuActivity.Companion.GRAPH_INFO
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.GraphInfo

/**
 * @see phamf.com.chemicalapp.ViewModel.MVVM_GraphDrawerActivityPresenter
 */
internal class GraphDrawerDataGetter(var view: Activity) {
    val data: GraphInfo
        get() {
            val bundle = view.intent.extras
            return bundle.getParcelable(GRAPH_INFO)
        }
}