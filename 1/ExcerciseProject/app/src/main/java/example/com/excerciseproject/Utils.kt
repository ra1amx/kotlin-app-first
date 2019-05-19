package example.com.excerciseproject

/**
 * Created by Mukhamed Issa on 5/19/19.
 */
object Utils {

    const val GENDER_MALE = "male"
    const val GENDER_FEMALE = "female"

    fun getItemsByGender(gender: String) = when (gender) {
        GENDER_MALE -> arrayListOf(Exercise("Male exercise 1"),
                Exercise("Male exercise 2"),
                Exercise("Male exercise 3"),
                Exercise("Male exercise 4"),
                Exercise("Male exercise 5"))
        GENDER_FEMALE -> arrayListOf(Exercise("Female exercise 1"),
                Exercise("Female exercise 2"),
                Exercise("Female exercise 3"),
                Exercise("Female exercise 4"),
                Exercise("Female exercise 5"))

        else -> throw IllegalArgumentException("Invalid gender: $gender")
    }

}