import org.junit.jupiter.api.*;

public class JUnitTest {
    @DisplayName("1+2是3")
    @Test
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a+b);
    }


    @DisplayName("1+3是4")
    @Test
    public void junitFailTest(){
        int a = 1;
        int b = 3;
        int sum = 4;
        System.out.println("1+3是4");
        Assertions.assertEquals(sum, a+b);
    }

    @BeforeEach
    public void prepare(){
        System.out.println("测试准备");
    }

    @AfterEach
    public void clean(){
        System.out.println("测试结束");
    }

    @BeforeAll
    public static void cleanAll(){
        System.out.println("最初准备");
    }

    @AfterAll
    public static void cleanAll2(){
        System.out.println("最后结束");
    }
}



