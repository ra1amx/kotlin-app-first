package example.com.excerciseproject.view

import example.com.excerciseproject.Work

/**
 * @since 2019
 * @author Anton Vlasov - whalemare with jun Ivan Eremin
 */
enum class WorkType {
    REPAIR {
        override fun getWorks(): List<Work> {
            return listOf(
                Work("Сломалась сантехника"),
                Work("Не показывает ТВ"),
                Work("Сломалась мебель"),
                Work("Нет электричества"),
                Work("Надо покрасить(забор/вернаду и т.д.)")
            )
        }
    },
    SERVICE {
        override fun getWorks(): List<Work> {
            return listOf(
                Work("Постричь газон"),
                Work("Вскопать участок"),
                Work("Вывезти мусор"),
                Work("Почистить канализацию"),
                Work("Настроить антенну/подключить ТВ")
            )
        }
    };

    abstract fun getWorks(): List<Work>
}