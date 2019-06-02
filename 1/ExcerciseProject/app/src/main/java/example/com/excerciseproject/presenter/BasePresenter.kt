package example.com.excerciseproject.presenter

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
abstract class BasePresenter<View> {

    var view: View? = null

    fun onAttach(view: View) {
        this.view = view
    }
}