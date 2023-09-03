package com.gorbatenkov.FriendLove.controllers;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.models.UserPhoto;
import com.gorbatenkov.FriendLove.services.RegistrationService;
import com.gorbatenkov.FriendLove.services.UserPhotoService;
import com.gorbatenkov.FriendLove.services.UserService;
import com.gorbatenkov.FriendLove.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * @author mikhail
 * Контроллер, отвечающий за обработку операций
 * аутентификации и регистрации пользователей.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final UserPhotoService userPhotoService;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UserValidator userValidator, UserService userService, UserPhotoService userPhotoService, RegistrationService registrationService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.userPhotoService = userPhotoService;
        this.registrationService = registrationService;
    }

    /**
     * Обрабатывает GET-запрос на /auth/login
     *
     * @return представление 'auth/login'
     */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    /**
     * Обрабатывает GET-запрос на /auth/registration.
     *
     * @param user атрибут, который будет добавлен в модель
     * @return представление 'auth/registration'
     */
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    /**
     * Константа, представляющая путь к директории, куда будут загружаться фотографии пользователей.
     * Путь строится на основе текущего рабочего каталога приложения.
     */
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\target\\classes\\static\\photos";

    /**
     * Обработка POST-запроса для регистрации пользователя в
     * Spring Boot приложении.
     *
     * @param user          объект для связи с данными формы
     * @param bindingResult ошибки валидации
     * @return перенаправление на страницу входа
     * @throws IOException исключение I/O, которое может возникнуть
     */
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) throws IOException {
        // Валидация объекта user с использованием userValidator
        userValidator.validate(user, bindingResult);

        /* Проверяем, есть ли ошибки валидации. Если есть,
        пользователь будет перенаправлен на страницу регистрации
        для исправления ошибок.*/
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        // Регистрирует пользователя с помощью registrationService и
        // получает идентификатор сохраненного пользователя.
        int saveUserId = registrationService.register(user);

        // Получает хэш-код пользователя, который будет использоваться
        // для создания уникальной директории пользователя.
        int UserId = userService.findById(saveUserId).get().hashCode();

        // Создает имя директории
        String directoryName = "user_" + UserId;

        // Создает объект Path, где будет храниться фотография пользователя.
        Path path = Paths.get(UPLOAD_DIRECTORY, directoryName);

        /* Проверяет, существует ли директория.
        Если она не существует, то создает ее, включая
        все необходимые поддиректории.*/
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (Files.exists(path)) {

            // Имя файла фотографии
            String photoName = "default";

            // Полный путь к файлу фотографии
            Path fileNameAndPath = Paths.get(path.toString(), photoName + ".png");
            // Строковое представление пути к файлу, в котором \
            // заменяются на прямые
            String fileNameAndPathToString = fileNameAndPath.toString().replace("\\", "/");

            // Часть, которая начинается с последнего вхождения /target
            String cuttedPath = fileNameAndPathToString.substring(fileNameAndPathToString.lastIndexOf("/target"));

            writePhoto(cuttedPath.substring(1));
            cuttedPath = cuttedPath.substring(cuttedPath.lastIndexOf("/photos"));

            // Информация о фотографии пользователя
            UserPhoto userPhoto = new UserPhoto();
            userPhoto.setUser(userService.findById(saveUserId).get());

            userPhoto.setPhotoPath(cuttedPath);
            userPhoto.setCreatedAt(LocalDateTime.now());
            userPhoto.setRating(0);
            userPhotoService.save(userPhoto);

            User user1 = userService.findById(saveUserId).get();
            user1.setAvatar(cuttedPath);
            userService.update(user1);
        } else {
            System.out.println("Загрузка завершилась неудачно!");
        }


        return "redirect:/auth/login";
    }

    /**
     * Копирует файл `default-avatar.png` из ресурсов classpath в указанный путь.
     * Читает файл в буфер и записывает его в новый файл.
     *
     * @param path путь к файлу
     * @throws IOException исключение I/O
     */
    private void writePhoto(String path) throws IOException {
        // Создаем объект ClassPathResource для получения ресурса из classpath
        ClassPathResource resource = new ClassPathResource("static/img/default-avatar.png");

        // Создаем файл, в который будем копировать изображение
        File copied = new File(path);

        // Открываем входной поток для чтения ресурса (изображения)
        try (
                InputStream in = new BufferedInputStream(resource.getInputStream());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(copied))) {

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }
}
