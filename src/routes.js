import Home from './pages/Home.vue'
import PlayList from './pages/PlayList.vue'
import Share from './pages/Share.vue'
import ViewPlayList from './pages/ViewPlayList.vue'
import Music from './pages/Music.vue'
import _type from './pages/_admin/_type.vue'
import _genre from './pages/_admin/_genre.vue'
import _home from './pages/_home.vue'
import Login from './pages/Login.vue'
import test from './pages/test.vue'
import Unauthorized from './pages/errors/Unauthorized.vue'
import Inscription from './pages/Inscription.vue'

const route = [
    { path : '/', name: 'login', component : Login },
    { path : '/inscription', name: 'inscription', component : Inscription },
    { path : '/home', name: 'home', component : Home, meta: {
            requireRole : 'auditor'
    }},
    { path : '/homeAdmin', name: 'homeAdmin', component : _home, meta: {
            requireRole : 'admin'
    }},
    { path : '/type', name: 'type', component : _type, meta: {
            requireRole : 'admin'
    }},
    { path : '/genre', name: 'genre', component : _genre, meta: {
            requireRole : 'admin'
    }},
    { path : '/playlist', name: 'playList', component : PlayList, meta: {
            requireRole : 'auditor'
    }},
    { path : '/share', name: 'share', component : Share, meta: {
            requireRole : 'auditor'
    }},
    { path : '/viewPlaylist:id', name: 'viewPlaylist', component : ViewPlayList, meta: {
            requireRole : 'auditor'
    }},
    { path : '/music', name: 'music', component : Music, meta: {
            requireRole : 'admin'
    }},
    { path : '/test', name: 'test', component : test, meta: {
            requireRole : 'admin'
    }},
    { path : '/unauthorized', name: 'unauthorized', component : Unauthorized }
];

export default route;