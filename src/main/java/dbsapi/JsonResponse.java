package dbsapi;

public class JsonResponse {
    private final Object data;

    public JsonResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }
}
