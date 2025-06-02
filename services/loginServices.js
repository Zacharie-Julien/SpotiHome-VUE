
export async function loginUser(login, password)
{
    const response = await fetch(`http://localhost:8080/simplyField/webresources/auth/login/${login}/passwd/${password}`, 
        {
            method: 'GET',
            credentials: 'include'
    });
    if (response.status == 200) {
        window.location.replace('http://localhost:5173/home');
    }
         

}