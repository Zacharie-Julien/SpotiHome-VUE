export async function getAllOperationById(id)
    {
        const response = await fetch(`http://localhost:8080/simplyField/webresources/operation/allOperationById/${id}`,{
            method: 'GET', 
            headers: {
                'Content-Type': 'application/json',
                'Authorization': document.cookie.slice(document.cookie.indexOf('JWT=') + 4),
            }
        });

        if (response.ok === true) {
            return response.json();
        }

        throw new Error('Impossible de contacter le serveur');           
    }