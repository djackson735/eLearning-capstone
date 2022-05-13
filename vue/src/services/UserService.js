import axios from 'axios';
export default {
    getUsers() {
        return axios.get('/admin/user-list')
    },
    
    getUserById(id) {
        return axios.get('/admin/' + id)
    },

    promoteToTeacher(id) {
        return axios.put('/admin/' + id)
    },

    checkTeacher(id) {
        return axios.get(`/user/${id}`)
    },

    deleteUser(id) {
        return axios.delete('/admin/' + id)
    }
}
