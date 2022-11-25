package com.springboot.example.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
public class AppResource {

    /*@Value("${ARGO-TOKEN}")
    private String argoToken;

    @Value("${APPLICATION-REPO}")
    private String applicationRepo;

    @Value("${APPLICATION}")
    private String application;*/

    @Autowired
    private AppRepository appRepository;

    @GetMapping("/apps/post")
    public String post() {
        URL url = null;
        try {
            url = new URL("http://webhook-eventsource-svc.argo-events:12000/deploy");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write("{\"message\":\"Dont call me Shirley\"}");
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "event raised";
    }

    @GetMapping("/apps")
    public List<App> retrieveAllApps() {
        return appRepository.findAll();
    }

    @GetMapping("/apps/{id}")
    public App retrieveApp(@PathVariable long id) {
        Optional<App> App = appRepository.findById(id);

        if (App.isEmpty())
            throw new AppNotFoundException("id-" + id);

        return App.get();
    }

    @DeleteMapping("/apps/{id}")
    public void deleteApp(@PathVariable long id) {
        appRepository.deleteById(id);
    }

    @PostMapping("/apps")
    public ResponseEntity<Object> createApp(@RequestBody App App) {
        App savedApp = appRepository.save(App);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedApp.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/apps/{id}")
    public ResponseEntity<Object> updateApp(@RequestBody App App, @PathVariable long id) {

        Optional<App> AppOptional = appRepository.findById(id);

        if (AppOptional.isEmpty())
            return ResponseEntity.notFound().build();

        App.setId(id);

        appRepository.save(App);

        return ResponseEntity.noContent().build();
    }
}
