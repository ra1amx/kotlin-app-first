package example.com.excerciseproject.view.select

import example.com.excerciseproject.R
import example.com.excerciseproject.Work
import kotlinx.android.synthetic.main.cell_select.view.*
import ru.whalemare.cells.ViewHolder
import ru.whalemare.cells.cell.CellDelegate

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class CellSelect: CellDelegate<Work>(R.layout.cell_select) {
    override fun isViewType(value: Any): Boolean {
        return value is Work
    }

    override fun bind(holder: ViewHolder, item: Work) {
        holder.itemView.checkbox.text = item.name
    }
}