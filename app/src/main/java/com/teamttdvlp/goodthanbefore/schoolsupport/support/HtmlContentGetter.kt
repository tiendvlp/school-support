package com.teamttdvlp.goodthanbefore.schoolsupport.support

object HtmlContentGetter {
    fun getContent (html : String) : String {
        var s = html
        while (s.contains("<")) {
            s = s.removeRange(s.indexOf("<"), s.indexOf(">") + 1)
        }
        return s
    }
}