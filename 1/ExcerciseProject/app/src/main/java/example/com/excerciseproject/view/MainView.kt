package example.com.excerciseproject.view

import example.com.excerciseproject.Work

/**
 * @since 2019
 * @author Anton Vlasov - whalemare with jun Ivan Eremin
 */
interface MainView {
    fun showWork(works: List<Work>)
    fun openEmailApp(title: String, body: String, email: String)
}