package com.example.housemaster

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainUI: MainUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Отображаем наш интерфейс
        mainUI = MainUI()
        mainUI.setContentView(this)

        // Подключаем верхнюю панель
        setSupportActionBar(mainUI.mToolbar)

        // Привязываем выдвижное меню к кнопке панели
        val toggle = ActionBarDrawerToggle(
            this, mainUI.mDrawer, mainUI.mToolbar,
            android.R.string.yes, android.R.string.no)
        mainUI.mDrawer.setDrawerListener(toggle)
        toggle.syncState()

        // Вешаем на элементы меню колбэки
        mainUI.menuItems.forEachIndexed { i, tv ->
            tv.onClick {
                mainUI.mDrawer.closeDrawers()
                loadFragment(mainUI.menuValues[i])
            }
        }

        // Загружаем дефолтный фрагмент
        loadFragment(mainUI.menuValues[0])
    }

    // Функция загрузки фрагмента
    fun loadFragment(seconds: Int) {
        val fragment = TimerFragment()

        val args = Bundle()
        args.putInt("seconds", seconds)
        fragment.arguments = args

        val ft = fragmentManager.beginTransaction()
        ft.replace(mainUI.mContainerId, fragment, "fragment")
        ft.commit()
    }
}
