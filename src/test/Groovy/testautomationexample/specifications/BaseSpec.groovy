package testautomationexample.specifications

import com.loyalty.testautomationexample.TestAutomationExample
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification
import com.loyalty.testautomationexample.utilities.Logger

@ActiveProfiles("mac")
@SpringBootTest(classes = TestAutomationExample)

class BaseSpec extends Specification {
    @Value('${test.data-path}') static String FILE
}
