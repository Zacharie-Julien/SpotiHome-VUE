
export async function loginUser(login, password)
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/auth/login/${login}/passwd/${password}`, 
        {
            method: 'GET',
            credentials: 'include'
    });
    if (response.status == 200) {
        if (JSON.parse(atob(document.cookie.split('.')[1])).role === 'admin') {
            window.location.replace('http://localhost:5173/homeAdmin');    
        }
        else {
            window.location.replace('http://localhost:5173/home');    
        }   
    }         
}