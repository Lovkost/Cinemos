package com.example.cinemos.ui.main.model

import android.media.Image
import android.widget.ImageView
import com.example.cinemos.R

data class MovieData(
//    val movie:Movie = getDefaultMovie()
    val title:String,
    val rating:Double,
    val budget:Int,
    val description:String,
    )
fun getMovie(): List<MovieData> {
    return listOf(
        MovieData(
            "Голгофа",
            7.7,
            7000000,
            "Фильм Джона Макдонаха на основе скандала вокруг священников-педофилов." +
                    " Отец Джеймс (Брендон Глисон) на очередной исповеди узнает от прихожанина," +
                    " что тот собирается убить его. За то, что сам много лет подвергался насилию" +
                    " со стороны священника-педофила. Джеймс может сбежать, может подать в суд на" +
                    " угрожающего, но и последнюю неделю он предпочитает тратить на то, чтобы помочь" +
                    " прихожанам найти Бога"
        ),
        MovieData(
            "Три билборда на границе Эббинга,Миссури",
            8.2,
            15000000,
            "Спустя несколько месяцев после убийства дочери Милдред Хейс преступники так и не найдены." +
                    " Отчаявшаяся женщина решается на смелый шаг - арендует на въезде в город три билборда и оставляет " +
                    "на них послание главе полиции Уильяму Уиллоуби. Когда в ситуацию оказывается втянут ещё и заместитель" +
                    " шерифа Диксон, инфантильный маменькин сынок со склонностью к насилию, борьба между Милдред и властями " +
                    "города только усугубляется."
        )
    )
}