package example.com.excerciseproject.view.order

import com.google.android.gms.maps.model.LatLng

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
interface ProcessOrderView {
    fun showCoordinate(coordinate: LatLng)
}