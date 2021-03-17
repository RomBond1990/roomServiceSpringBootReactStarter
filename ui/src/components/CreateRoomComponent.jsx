import React, {Component} from 'react';
import RoomService from '../services/RoomService'

class CreateRoomComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            name: '',
            country: ''
        }

        this.changeRoomNameHandler = this.changeRoomNameHandler.bind(this);
        this.changeCountryHandler = this.changeCountryHandler.bind(this);
        this.saveOrUpdateRoom = this.saveOrUpdateRoom.bind(this);
    }

    componentDidMount() {
        if (this.state.id === '_add') {
            return
        } else {
            RoomService.getRoomById(this.state.id).then((res) => {
                let room = res.data;
                this.setState({
                    name: room.name,
                    country: room.country
                });
            });
        }
    }

    changeRoomNameHandler = (event) => {
        this.setState({name: event.target.value});
    }

    changeCountryHandler = (event) => {
        this.setState({country: event.target.value});
    }

    saveOrUpdateRoom = (event) => {
        event.preventDefault();
        let room = {name: this.state.name, country: this.state.country};

        if (this.state.id === '_add') {
            RoomService.createRoom(room).then(res => {
                this.props.history.push('/api/rooms')
            })
        } else {
            RoomService.updateRoom(room, this.state.id).then(res => {
                this.props.history.push('/api/rooms')
            });
        }
    }

    cancel() {
        this.props.history.push('/api/rooms')
    }

    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add Room</h3>
        } else {
            return <h3 className="text-center">Update Room</h3>
        }
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Room name</label>
                                        <input placeholder="Room name" name="name" className="form-control"
                                               value={this.state.name} onChange={this.changeRoomNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Country</label>
                                        <input placeholder="Country" name="country" className="form-control"
                                               value={this.state.country} onChange={this.changeCountryHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveOrUpdateRoom}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateRoomComponent;
