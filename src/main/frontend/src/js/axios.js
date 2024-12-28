import axios from "axios";
const customApi = ()=>{
    const axiosInstance =  axios.create({
        baseURL: 'http://localhost:8080/REST/v2',
        // headers: {
        //     "Cache-Control": "no-cache",
        //     "Content-Type": "application/json",
        //     "Access-Control-Allow-Origin": "http://localhost:8080",
        // },
    })
    axiosInstance.interceptors.request.use((conf)=>{
        // conf.headers['Access-Control-Allow-Origin'] = 'http://localhost:9090'
        return conf;
    })
    return axiosInstance
}
export default customApi();