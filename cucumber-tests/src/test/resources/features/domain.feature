# language: ru

Функциональность: Логика предметной области
  Предыстория:
    Пусть пользователь открыл страницу "/login"
    Когда пользователь вводит в поле ввода "username" значение "yura"
    И пользователь вводит в поле ввода "password" значение "123"
    И пользователь нажимает кнопку формы "Войти"
    Тогда открыта страница "/"
    И пользователь авторизован под логином "yura"
    
  Сценарий: Просмотр книг
    Когда пользователь нажимает кнопку "Все книги"
    Тогда открыта страница "/book/show"