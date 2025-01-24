import data.repositories.UserRepository
import database.user.UserDao

/*class UserRepositoryTest {

    private lateinit var userRepository: UserRepository
    private lateinit var mockUserDao: UserDao

    @Before
    fun setup() {
        mockUserDao = mockk()
        userRepository = UserRepositoryImpl(mockUserDao)
    }

    @Test
    fun `test login returns success for valid credentials`() = runBlocking {
        val email = "onoff@gmail.com"
        val password = "test1234"

        val result = userRepository.login(email, password)

        assertTrue(result.isSuccess)
        assertEquals(true, result.getOrNull())
    }

    @Test
    fun `test login returns failure for invalid credentials`() = runBlocking {
        val email = "wrong@gmail.com"
        val password = "wrongpassword"

        val result = userRepository.login(email, password)

        assertTrue(result.isFailure)
    }
}

 */
