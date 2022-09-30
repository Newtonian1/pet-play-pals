import axios from "axios";

export default{
createPlayDate(newPlayDate){
    return axios.post('/playdates', newPlayDate);
},
getPlayDateById(playDateId){
    return axios.get(`/playdates/${playDateId}`);
},
updatePlayDate(updatedPlayDate,playDateId){
    return axios.put(`/playdates/${playDateId}`, updatedPlayDate);
},
deletePlayDate(playDateId){
    return axios.delete(`/playdates/${playDateId}`);
}


}