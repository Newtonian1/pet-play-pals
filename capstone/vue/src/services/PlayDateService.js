import axios from "axios";

export default{
createPlayDate(newPlayDate){
    return axios.post('/playdates', newPlayDate)
},

}