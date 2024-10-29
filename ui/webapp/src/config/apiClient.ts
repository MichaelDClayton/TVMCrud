import axios from "axios";

const apiClient = axios.create({
    baseUrl: "http://localhost:8080"
    })

export default apiClient;