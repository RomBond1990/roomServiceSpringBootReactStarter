import React, {Component} from 'react';
import RoomService from '../services/RoomService'


class RoomsComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            rooms: []
        }

        this.editRoom = this.editRoom.bind(this);
        this.deleteRoom = this.deleteRoom.bind(this);
    }

    componentDidMount() {
        RoomService.getRooms().then((res) => {
            this.setState({ rooms: res.data});
        });
    }

    editRoom(id){
        this.props.history.push(`/add-room/${id}`);
    }
    deleteRoom(id){
        RoomService.deleteRoom(id).then( res => {
            this.setState({rooms: this.state.rooms.filter(room => room.id !== id)});
        });
    }
    viewRoom(id){
        this.props.history.push(`/view-room/${id}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Rooms</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Room name</th>
                            <th>Country</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.rooms.map(
                                room =>
                                    <tr key={room.id}>
                                        <td>{room.name}</td>
                                        <td>{room.country}</td>
                                        <td>
                                            <button onClick={ () => this.editRoom(room.id)} className="btn btn-info">Update </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () => this.deleteRoom(room.id)} className="btn btn-danger">Delete </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () => this.viewRoom(room.id)} className="btn btn-info">Enter </button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default RoomsComponent;
