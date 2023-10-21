# language: ru
Функциональность: Регистрация
  Предыстория:
    Пусть пользователь открыл страницу "/"
    И пользователь не авторизован
    Когда пользователь нажимает кнопку "Войти"
    Затем пользователь нажимает на ссылку "Регистрация"
    Тогда открыта страница "/register"

  Сценарий: Успешная регистрация
    Когда пользователь вводит в поле ввода "username" значение "yura"
    И пользователь вводит в поле ввода "password" значение "123"
    И пользователь нажимает кнопку формы "Register"
    Тогда открыта страница "/login"
    Когда пользователь вводит в поле ввода "username" значение "yura"
    И пользователь вводит в поле ввода "password" значение "123"
    И пользователь нажимает кнопку формы "Войти"
    Тогда открыта страница "/"
    И пользователь авторизован под логином "yura"