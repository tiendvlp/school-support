package com.teamttdvlp.goodthanbefore.schoolsupport.model.functions

import android.util.Log
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalequation.ChemicalEquation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.select.Elements



class OnlineSearchChemEquaManager (
        var onReachASearch : OnReachASearch,

        var onSearchSucess : OnSearchSucess ){

    fun search (link : String, page_num : Int = 1) {
        Log.e("Run :((", "Please")
        var stillHaveEquation = true

        var observer = Observable.just(link)
                .observeOn(Schedulers.newThread())
                .map {
                    hyper_link ->
                    var list = ArrayList<ChemicalEquation>()
                    if (hyper_link != "") {
                        var doc = Jsoup.connect(hyper_link).get()
                        Log.e("Link", hyper_link + "a")
                        var elements: Elements = doc.select("div.full-formula")

                        if (elements.size == 0) {
                            stillHaveEquation = false

                            Observable.just("").observeOn(AndroidSchedulers.mainThread()).map {
                                onSearchSucess()
                            }.subscribe()

                            Log.e("Still have equa", stillHaveEquation.toString())
                        }

                        for (i in 0..elements.size - 1) {
                            var equation = ChemicalEquation()
                            list.add(equation)
                        }

                        for ((i, big_element) in elements.withIndex()) {

                            var small_element = big_element.getElementsByTag("tr").first()

                            var elements : Elements = small_element.getElementsByTag("td")

                            for (mini_element in elements) {
                                var content = mini_element.html()
                                if (content.contains("<")) {
                                    var end_pos = content.indexOf(">") + 1
                                    content = content.removeRange(0, end_pos)
                                }
                                list[i].equation += content
                            }
                            list[i].equation = cleanContentWay2(list[i].equation)
                        }

                        var observer3 = Observable.just(hyper_link).observeOn(Schedulers.newThread())
                                .map<Elements> {
                                    var doc = Jsoup.connect(hyper_link).get().select("div.tab-content")
                                    doc
                                }.subscribe {
                                    var p = -1
                                    for (sub_elements in it) {
                                        var super_elements = sub_elements.select("div.tab-pane").first()

                                        if (super_elements.allElements.size > 1) {
                                            var all_super_elements = super_elements.allElements

                                            for ((i, element) in all_super_elements.withIndex()) {
                                                if ((i > 0) and (i < all_super_elements.size - 1)) {
                                                    var content = element.html()
                                                    var next_content = all_super_elements[i + 1].html()
                                                    if (content.contains("Điều kiện phản ứng")) {
                                                        p++
                                                        list[p].condition = next_content
                                                    } else if (content.contains("Hiện tượng nhận biết")) {
                                                        list[p].phenonema = next_content
                                                    } else if (content.contains("Cách thực hiện phản ứng")) {
                                                        list[p].howToDo = next_content
                                                    }
                                                }
                                            }
                                        } else {
                                            throw Exception("BIG ERROR: " + "CONTENT JUST HAVE 1 ELEMENT")
                                        }
                                    }

                                    onReachASearch(list)

                                    if (stillHaveEquation) {
                                        if (page_num == 1) {
                                            OnlineSearchChemEquaManager(onReachASearch, onSearchSucess)
                                                    .search(link + "&page=2", page_num + 1)
                                        } else {
                                            OnlineSearchChemEquaManager(onReachASearch, onSearchSucess)
                                                    .search(link.substring(0
                                                            , link.lastIndexOf("=") + 1) + (page_num + 1).toString()
                                                            ,page_num + 1)
                                        }
                                    }
                                }
                    }
                    else {
                        Log.e("What happen", "i don't know")
                    }
                    return@map list
                }.observeOn(AndroidSchedulers.mainThread()).map {
                    try {
                        if (it.size != 0) {

                        }
                    } catch (ex : Exception) {
                        ex.printStackTrace()
                    }


                }.subscribe({ },
                        {
                            it.printStackTrace()
                        }, {

                })
    }
}

fun cleanContentWay2 (content : String) : String {
    var s = content
    s = s.replace("<sup></sup>", "")
    s = s.replace("<span class=\"highlight\">", "")
    s = s.replace("<span class=\"glyphicon glyphicon-arrow-up\" style=\"color:black\"> </span>", "")
    s = s.replace("<span class=\"glyphicon glyphicon-arrow-down\" style=\"color:black\"> </span>", "")
    return s
}

typealias OnReachASearch = (list : ArrayList<ChemicalEquation>) -> Unit

typealias OnSearchSucess = () -> Unit