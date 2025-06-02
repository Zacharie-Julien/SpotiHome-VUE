<template>
    <Card title="Login" class="login">
        <template v-slot:main>
            <div class="mainLogin">
                <form>
                    <input type="text" class="login" v-model="login" placeholder="Identifiant">
                    <input type="password" class="password" v-model="password" placeholder="Mot de passe">
                    <button type="button" v-on:click="loginUser(login, password)">Login</button>
                </form>
            </div>            
        </template>        
    </Card>

</template>

<script setup>

    import { ref } from 'vue';
    import Card from '../components/Card.vue';
    import { loginUser } from '../services/loginServices';


    const login = ref('');
    const password = ref('');

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

        display: flex;
        align-items: center !important;
        justify-content: center !important;
    }


</style>