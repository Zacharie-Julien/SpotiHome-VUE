import Domains from './pages/Domains.vue';
import Unauthorized from './pages/errors/Unauthorized.vue';
import Field from './pages/Field.vue';
import Home from './pages/Home.vue'
import Login from './pages/Login.vue';
import Treatment from './pages/Treatment.vue';
import Workers from './pages/Workers.vue';

const route = [
    { path : '/', name: 'login', component : Login },
    { path : '/home', name: 'home', component : Home, meta: {
            requireRole : 'admin'
    } },
    { path : '/field', name: 'field', component : Field, meta: {
            requireRole : 'admin'
    }},
    { path : '/workers', name: 'workers', component : Workers, meta: {
            requireRole : 'admin'
    }},
    { path : '/domains', name: 'domains', component : Domains, meta: {
            requireRole : 'admin'
    }},
    { path : '/treatment', name: 'treatment', component : Treatment, meta: {
            requireRole : 'admin'
    }},     
    { path : '/unauthorized', name: 'unauthorized', component : Unauthorized }
];

export default route;