package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "jsonHttpRequest", urlPatterns = "/request-body-string")
public class JsonHttpRequest extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream requestInputStream = request.getInputStream();
        String copyToString = StreamUtils.copyToString(requestInputStream, StandardCharsets.UTF_8);
        System.out.println("copyToString = " + copyToString);

        HelloData helloData = objectMapper.readValue(copyToString, HelloData.class);
        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("Ok");
    }
}
