package com.nyanmeter.springboot.web;

import com.nyanmeter.springboot.model.Cat;
import com.nyanmeter.springboot.model.CatRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class CatWebService {

    final CatRepository repository;

    public CatWebService(CatRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/recalc")
    public void recalc(@RequestBody List<Cat> cats) {
        repository.saveAll(cats);
    }

    @PostMapping(value = "/add")
    public RedirectView add(@RequestBody MultipartFile multipartFile, @RequestParam String name) {
        try {
            addInternal(multipartFile, name);
        } catch (IOException e) {
            return new RedirectView("adding-failure.html");
        }

        return new RedirectView("adding-success.html");
    }

    private void addInternal(MultipartFile multipartFile, String name) throws IOException {
        long count = repository.count() + 1;

        File file = new File("src/main/webapp/cats/" + count + ".jpg");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }

        Cat cat = new Cat(count, name, "cats" + count);
        repository.save(cat);
    }

    @GetMapping(value = "/list")
    public List<Cat> listCats() {
        List<Cat> list = (List<Cat>) repository.findAll();
        Collections.shuffle(list);

        return list.subList(0, 10);
    }

    @GetMapping(value = "/rating")
    public List<Cat> getRating() {
        return ((List<Cat>) repository.findAll())
                .stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }
}
