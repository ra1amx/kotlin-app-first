package example.com.excerciseproject.view

import example.com.excerciseproject.Work

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
enum class WorkType {
    REPAIR {
        override fun getWorks(): List<Work> {
            return listOf(
                Work("Male exercise 1"),
                Work("Male exercise 2"),
                Work("Male exercise 3"),
                Work("Male exercise 4"),
                Work("Male exercise 5")
            )
        }
    },
    SERVICE {
        override fun getWorks(): List<Work> {
            return listOf(
                Work("Female exercise 1"),
                Work("Female exercise 2"),
                Work("Female exercise 3"),
                Work("Female exercise 4"),
                Work("Female exercise 5")
            )
        }
    };

    abstract fun getWorks(): List<Work>
}