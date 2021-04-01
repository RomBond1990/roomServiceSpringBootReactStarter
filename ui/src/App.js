import './App.css';
import {HashRouter, Route, Switch} from 'react-router-dom'
import RoomsComponent from "./components/RoomsComponent";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import CreateRoomComponent from "./components/CreateRoomComponent";
import RoomComponent from "./components/RoomComponent";

function App() {
    return (<div>
            <HashRouter>
                <HeaderComponent/>
                <div className="container">
                    <Switch>
                        <Route path = "/" exact component = {RoomsComponent}/>
                        <Route path = "/api/rooms" component = {RoomsComponent}/>
                        <Route path = "/add-room/:id" component = {CreateRoomComponent}/>
                        <Route path = "/view-room/:id" component = {RoomComponent}/>
                    </Switch>
                </div>
                <FooterComponent/>
            </HashRouter>
        </div>
    );
}

export default App;
