# Лабораторная работа №3. Lifecycle компоненты. Навигация в приложении.

### Цели

- Ознакомиться с методом обработки жизненного цикла activity/fragment при помощи Lifecycle-Aware компонентов
- Изучить основные возможности навигации внутри приложения: создание новых activity, navigation graph

### Задачи


#### Задача 1. Обработка жизненного цикла с помощью Lifecycle-Aware компонентов
---
**Задание**: Ознакомиться с Lifecycle-Aware Components по документации: https://developer.android.com/topic/libraries/architecture/lifecycle и выполнить codelabs (ссылка внизу страницы в разделе codelabs).

В данном задании осуществляется знакомсто с пакетом androidx.lifecycle. Он предоставляет классы и интерфейсы, которые позволяют создавать компоненты с учетом жизненного цикла, т.е спообоные автоматически настраивать свое поведение в зависимости от текущего состояния жизненного цикла действия или фрагмента. Компоненты, которые в дальнейшем подробно рассматриваются:
ViewModel - класс, предоставляющий способ создавать и извлекать объекты, привязанные к определенному жизненному циклу. Он позволяет сохраняться данным при изменении конфигурации устройства. 
LifecycleOwner - интерфейс, реализованный классами AppCompatActivity и Fragment. У него есть один метод, getLifecycle (), который возвращает объект Lifecycle. На этот объект можно подписать слушателей, которые будут получать уведомления при смене lifecycle-состояния Activity.
LiveData - хранилище данных, позоляющее поместить какой-либо объект и подписаться на себя, предоставляя подписчикам помещенные объекты.

В программе реализован таймер, значение которого сбрасыватся при повороте экрана. Это говорит о том, что Activity - плохой выбор для управления данными приложения, так как это недолговечные объект, который часто создается и уничтожается при взаимодействии пользователя с приложением. 
ViewModel  же поддерживается до тех пор, пока активна область LifecycleOwner. Он не уничтожается из-за я изменения конфигурации, например поворота экрана. Новый экземпляр владельца повторно подключается к существующей ViewModel.

Из данного задания и из опыта предыдущей лаб. работы было выяснено, что необходимо избегать полей, которые ссылаются на экземпляры классов Context или View. Вместо этого необходимо настраивать действие или фрагмент для наблюдения за источником данных, получая данные при их изменении. 

Объект-владелец жизненного цикла можно передать новым экземплярам компонентов, учитывающих жизненный цикл, чтобы они знали о текущем состоянии жизненного цикла.

Объект, реализующий LifecycleObserver,  может отслеживать изменения в состоянии владельца жизненного цикла.


#### Задача 2. Навигация (startActivityForResult)
---
**Задание**: Реализоать навигацию между экранами одного приложения согласно изображению ниже с помощью Activity, Intent и метода startActivityForResult.

*Рис 2.1 Task_2*

![image_task2_3](https://github.com/Julia0028/android_lab_3/blob/master/pictures/3.png)

**Решение:**
Созданы 4 класса: AboutActivity, FirstActivity, SecondActivity и ThirdActivity. Их связь с соответствующими layout'ами осуществляется с помощью инструмента binding. Он генерирует binding класс, объект которого содержит ссылки на все view из файла, для которых указан android:id. 
Навигация между экранами осуществляется при помощь класса Intent. Intent – это объект, в котором мы прописываем, какое Activity нам необходимо вызвать. После чего мы передаем этот Intent-объект методу startActivity, который находит соответствующее Activity и показывает его. 
Чтобы избежать переполнения стека, с открытием новой Activity нужно завершать текущую методом finish(). Метод startActivityForResult() позволяет при этом вытащить результат Activity. В данном случае при нажатии кнопки в ThirdActivity, она будет завершаться и передавать управление SecondActivity, но в зависимости от кнопки будет подаваться сигнал, нужно ли также завершить SecondActivity или нет.
Также согласно варианту 18 была создана ActivityAbout, которая доступна из всех Activity через Options Menu.
Если не указать все Activty в Манифесте, то приложение закроется при попытке перейти в незарегистрированное Activity.


#### Задача 3. Навигация (флаги Intent/атрибуты Activity)
---
**Задание**: Решить предыдущую задачу с помощью Activity, Intent и флагов Intent либо атрибутов Activity.
Вместо того, чтобы использовать методы startActivityForResult и setResult, можно использовать Intent-флаги.

**Решение:**
Intent-флаг FLAG_ACTIVITY_CLEAR_TOP
В FirstActivity логика не меняется. Нужно было изменить логику перехода из ThirdActivity в SecondActivity, чтобы избавиться от методов возвращения результата.
Теперь FirstActivity запускается непосредственно из ThirdActivity c указанным флагом. Благодаря нему, FirstActivity открывается, так как она уже была в стеке, а все, что выше нее – закрывается.


#### Задача 4. Навигация (флаги Intent/атрибуты Activity)
---
**Задание**: Дополнить граф навигации новым(-и) переходом(-ами) с целью демонстрации какого-нибудь (на свое усмотрение) атрибута Activity или флага Intent, который еще не использовался для решения задачи. Поясните пример и работу флага/атрибута.
Ограничение на размер backstack к этому и следующему заданию не применяется.

**Решение:**
Intent-флаг FLAG_ACTIVITY_SINGLE_TOP
Если мы вызываем Activity, и оно уже находится в топе стека, то новый экземпляр этого Activity создан не будет. Мы попадем в старый и получим там вызов метода onNewIntent().
На скринах ниже происходит переход из FirstActivity в SecondActivity. По нажатию кнопки Push me происходит пересоздание SecondActivity. 
![image_task4_1](https://github.com/Julia0028/android_lab_3/blob/master/pictures/1.png)

После добавления флага в логе можно увидеть, что SecondActivity больше не пересоздается.
![image_task4_2](https://github.com/Julia0028/android_lab_3/blob/master/pictures/2.png)


#### Задача 5. Навигация (Fragments, Navigation Graph)
---
**Задание**: Решить предыдущую задачу (с расширенным графом) с использованием navigation graph. Все Activity должны быть заменены на фрагменты, кроме Activity 'About', которая должна остаться самостоятельной Activity. В отчете сравните все решения.

**Решение:**
Все Activity были заменены на Fragment, была создана отдельная MainActivity, в которой устанавливаются Options Menu и контейнер для Fragment. В Fragment реализована связь между ними (навигация осуществляется при помощи методов findNavController (поиск NavController связанного с кнопкой) и navigate.

Визуализация связей между фрагментами представлена в ресурсе navigation в виде Navigation Graph:
![image_task5_4](https://github.com/Julia0028/android_lab_3/blob/master/pictures/4.png)


### Выводы
В данной работе осуществлено знакомсто с пакетом androidx.lifecycle. Он предоставляет классы и интерфейсы, которые позволяют создавать компоненты с учетом жизненного цикла, т.е спообоные автоматически настраивать свое поведение в зависимости от текущего состояния жизненного цикла действия или фрагмента. Была подробно изучена работа компонентов: ViewModel, LifecycleOwner и LiveData. 
Также были изученые различные методы навигации в приложении:
1. С помощью стандартных методов Android SDK: startActivity(), finish(), startActivityForResult(), onActivityResult();
2. Intent-флагов (сократили количество кода по ср. с п.1 и сделали его более читабельным);
3. С помощью фрагментов (наиболее комфортная работа с возможностью визуализации связей между экранами)

### Приложение
**task_2.FirstActivity**
```
package com.example.lab_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.lab_3.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToSecond.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SecondActivity::class.java
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_but -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
```
**task_2.SecondActivity**
```
package com.example.lab_3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    val START_THIRD = 0
    val RETURN_FIRST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToFirst.setOnClickListener { finish() }
        binding.butToThird.setOnClickListener { startActivityForResult(Intent(this, ThirdActivity::class.java), START_THIRD) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == START_THIRD) {
            if (resultCode == RETURN_FIRST) {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_but -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
```
**task_2.ThirdActivity**
```
package com.example.lab_3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityThirdBinding

class ThirdActivity: AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    val RETURN_FIRST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToFirstFromThird.setOnClickListener {
            setResult(RETURN_FIRST)
            finish()
        }
        binding.butToSecFromThird.setOnClickListener { finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_but -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
```
**task_2.AboutActivity**
```
package com.example.lab_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityAboutBinding

class AboutActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
```
**task_3.FirstActivity**
```
package com.example.lab_3.task_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityFirstBinding

class FirstActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

}
```
**task_3.SecondActivity**
```
package com.example.lab_3.task_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToFirst.setOnClickListener { finish() }
        binding.butToThird.setOnClickListener { startActivity(Intent(this, ThirdActivity::class.java)) }
    }
}
```
**task_3.ThirdActivity**
```
package com.example.lab_3.task_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityThirdBinding

class ThirdActivity: AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToFirstFromThird.setOnClickListener {

            startActivity(Intent(this, FirstActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        binding.butToSecFromThird.setOnClickListener { finish() }
    }
}
```
**task_4.FirstActivity**
```
package com.example.lab_3.task_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivityFirstBinding


class FirstActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FirstActivity", "FirstActivity created")
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butToSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
```
**task_4.SecondActivity**
```
package com.example.lab_3.task_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.databinding.ActivitySecond4Binding

class SecondActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySecond4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SecondActivity", "SecondActivity created")
        binding = ActivitySecond4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("SecondActivity", "onNewIntent() created")
    }
}
```
**task_5.MainActivity**
```
package com.example.lab_3.task_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_3.R
import com.example.lab_3.databinding.ActivityMainBinding
import com.example.lab_3.task_2.AboutActivity

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_but -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
```
**task_5.FirstFragment**
```
package com.example.lab_3.task_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.lab_3.R
import com.example.lab_3.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)

        binding.button2.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return binding.root
    }
}
```
**task_5.SecondFragment**
```
package com.example.lab_3.task_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lab_3.R
import com.example.lab_3.databinding.FragmentFirstBinding
import com.example.lab_3.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        binding.button3.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_secondFragment_to_firstFragment)
        }
        binding.button4.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        return binding.root
    }
}
```
**task_5.ThirdFragment**
```
package com.example.lab_3.task_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lab_3.R
import com.example.lab_3.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentThirdBinding.inflate(layoutInflater)
        binding.button5.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_thirdFragment_to_firstFragment)
        }
        binding.button6.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_thirdFragment_to_secondFragment)
        }
        return binding.root
    }
}
```