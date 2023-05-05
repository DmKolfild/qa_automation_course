# Test case for www.habr.com

### The coincidence of the article title with the preview on the main page
1. Open https://www.habr.com/
2. Find the news block
3. Find the first article in this block
4. Click on the first article

Expected result: the title from the 4 step have the same title as in the 3 step
Note: The name of the test case is "newsSimilarHeaders".

---

### Get an error message when users enter an incorrect email
1. Open https://www.habr.com/
2. Click on the profile icon
3. Click the button "Войти"
4. Enter "123" in the email field
5. Click "Enter"

Expected result: the user received the message "Введите корректный e-mail"
Note: The name of the test case is "signInIncorrectEmail".
