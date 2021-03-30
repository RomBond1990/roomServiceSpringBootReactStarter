import React, {Component} from 'react';
import RoomService from "../services/RoomService";

class RoomComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
            bulb: '',
            country: '',
            message: ''
        }
        this.lightUp = this.lightUp.bind(this);
        this.turnOff = this.turnOff.bind(this);
    }

    lightUp = (event) => {
        event.preventDefault();
        this.setState({bulb: true}, () => {
            let room = {name: this.state.name, bulb: this.state.bulb, country: this.state.country};
            RoomService.updateRoom(room, this.state.id);
        });
    }

    turnOff = (event) => {
        event.preventDefault();
        this.setState({bulb: false}, () => {
            let room = {name: this.state.name, bulb: this.state.bulb, country: this.state.country};
            RoomService.updateRoom(room, this.state.id);
        });
    }

    componentWillMount() {
        this.getData()
    }

    getData() {
        RoomService.getRoomById(this.state.id)
            .then((res) => {
            let room = res.data;
            this.setState({
                name: room.name,
                bulb: room.bulb,
                country: room.country,
                message: room.message
            });
        });
    }

    bulbLight() {
        if (this.state.bulb) {
            return <h3 className="">Light is on</h3>
        } else {
            return <h3 className="">Light is off</h3>
        }

    }

    enterRoom() {
        if (this.state.message === undefined) {
            return (<div>
                {this.bulbLight()}
                <button className="btn btn-success" onClick={this.lightUp}>On</button>
                <button className="btn btn-danger" onClick={this.turnOff}>Off</button>
            </div>)
        }
        if (typeof this.state.message != undefined) {
                    return <h3>{this.state.message}</h3>
                }
    }


    render() {
        return (
            <div className="container">
                {this.enterRoom()}
            </div>
        );
    }
}

export default RoomComponent;
