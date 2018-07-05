package dbsapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public JsonResponse index() {
        return new JsonResponse("Hello, world");
    }
}
