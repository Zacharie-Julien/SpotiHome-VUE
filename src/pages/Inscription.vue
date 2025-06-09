<template>
    <Card title="Inscription" class="login">
        <template v-slot:main>
            <form class="mainLogin">
                <input type="text" v-model="login" placeholder="Identifiant">
                <input type="text" class="password" v-model="email" placeholder="Mail">
                <input type="password" class="password" v-model="password" placeholder="Entrer votre mot de passe">
                <input type="password" class="password" v-model="password2" placeholder="Confirmer votre mot de passe">
                <div>
                    <RouterLink to="/">Connexion</RouterLink>
                    <p>ou</p>
                    <RouterLink to="/inscription">Inscription</RouterLink>
                </div>                
            </form>
        </template>        
    </Card>

</template>

<script setup>

    import { ref } from 'vue'
    import Card from '../components/Card.vue'
    import { loginUser } from '../services/loginServices'
    import { useRouter } from 'vue-router'

    const router = useRouter();
    const login = ref('');
    const password = ref('');
    const password2 = ref('');
    const email = ref('');

</script>

<style scoped> 

    .login 
    {
        width: 100%;
        height: 100%;
    }

    .mainLogin 
    {
        width: 100%;
        height: 100%;
        gap: 30px;

        display: flex;
        flex-direction: column;
        align-items: center !important;
        justify-content: center !important;
    }

    .mainLogin  > div
    {
        width: 300px;
        display: flex;

        gap: 15px;
        align-items: center;
        justify-content: center;
    }

    .mainLogin > div > button, p, a
    {
        background-color: transparent;
        border: none;

        color: var(--main-title-gray);
        text-decoration: none;
        list-style: none;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-optical-sizing: auto;
        font-weight: 100;
        font-style: normal;
        font-size: 20px;
        word-spacing: 1px;
        transition-duration: .125s;
    }

    .mainLogin > div > button:hover, a:hover
    {
        color: var(--main-green);
        cursor: pointer;
    }

</style>