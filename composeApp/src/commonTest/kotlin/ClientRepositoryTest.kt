/*class ClientRepositoryTest {

    private lateinit var clientRepository: ClientRepository
    private lateinit var mockClientDao: ClientDao

    @Before
    fun setup() {
        mockClientDao = mockk()
        clientRepository = ClientRepositoryImpl(mockClientDao)
    }

    @Test
    fun `test getClients returns success`() = runBlocking {
        val mockClients = listOf(Client(1, "John Doe", "john@example.com", "123456789"))
        coEvery { mockClientDao.getAllClients() } returns mockClients

        val result = clientRepository.getClients()

        assertTrue(result.isSuccess)
        assertEquals(mockClients, result.getOrNull())
    }

    @Test
    fun `test getClients returns failure`() = runBlocking {
        coEvery { mockClientDao.getAllClients() } throws Exception("Database error")

        val result = clientRepository.getClients()

        assertTrue(result.isFailure)
    }
}

 */