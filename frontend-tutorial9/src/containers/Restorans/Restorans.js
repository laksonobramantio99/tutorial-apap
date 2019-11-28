import React, {Component} from 'react';
import classes from "./Restorans.module.css";
import Restoran from '../../components/Restoran/Restoran.js';
import axios from '../../axios-restoran.js';
import Modal from '../../components/UI/Modal/Modal.js';
import Button from '../../components/UI/Button/Button';

class Restorans extends Component {
    constructor(props) {
        super(props);
        this.state = {
            restorans: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,

            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: ""
        }
    };

    componentDidMount() {
        console.log("componentDidMount()");
        this.loadRestorans();
    };

    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    };

    render() {
        console.log("render()");
        return (
            <React.Fragment>
                <Modal show={this.state.isCreate || this.state.isEdit}
                    modalClosed={this.canceledHandler}>
                    {this.renderForm()}
                </Modal>

                <div className={classes.Title}>All Restorans</div>
                <div className={classes.ButtonLayout}>
                    <button
                        className={classes.AddRestoranButton}
                        onClick={this.addRestoranHandler}
                    >
                        + Add New Restoran
                    </button>
                </div>
                <div className={classes.Restorans}>
                    {this.state.restorans &&
                        this.state.restorans.map(restoran =>
                            <Restoran
                                key={restoran.id}
                                nama={restoran.nama}
                                alamat={restoran.alamat}
                                nomorTelepon={restoran.nomorTelepon}
                                edit={() => this.editRestoranHadler(restoran)}
                                delete={() => this.deleteRestoranHandler(restoran.idRestoran)}
                            />
                        )
                    }
                </div>
            </React.Fragment>
        );
    };

    renderForm() {
        const {isEdit} = this.state;
        return (
            <form>
                <input 
                    className={classes.Input} 
                    name="nama" 
                    type="text" 
                    placeholder="Nama"
                    value={this.state.nama}
                    onChange={this.changeHandler}
                />
                <input 
                    className={classes.Input} 
                    name="nomorTelepon" 
                    type="number"
                    placeholder="Nomor Telepon"
                    value={this.state.nomorTelepon}
                    onChange={this.changeHandler}
                />
                <textarea 
                    className={classes.TextArea} 
                    name="alamat" 
                    type="text"
                    placeholder="Alamat"
                    value={this.state.alamat}
                    onChange={this.changeHandler}
                />
                <input 
                    className={classes.Input} 
                    name="rating" 
                    type="number"
                    placeholder="Rating"
                    value={this.state.rating}
                    onChange={this.changeHandler}
                />
                <Button btnType="Danger" onClick={this.canceledHandler}>CANCEL</Button>
                <Button btnType="Success" 
                        onClick={isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler}>
                    SUBMIT    
                </Button>
            </form>
        )
    };

    // Handler untuk perubahan setiap value pada form
    changeHandler = event => {
        // name dari property name di input
        const {name, value} = event.target;
        this.setState({ [name]: value })
    };

    submitAddRestoranHandler = event => {
        event.preventDefault();
        this.setState({ isLoading: true });
        this.addRestoran();
        this.canceledHandler();
        this.clearForm();
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };
        console.log("masuk addRestoran()")

        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }

    editRestoranHadler(restoran) {
        this.setState({
            isEdit:true,
            idRestoran: restoran.idRestoran,
            nama: restoran.nama,
            nomorTelepon: restoran.nomorTelepon,
            rating: restoran.rating,
            alamat: restoran.alamat
        })
    }

    submitEditRestoranHandler = event => {
        console.log("editing");
        event.preventDefault();
        this.setState({isLoading: true});
        this.editRestoran();
        this.canceledHandler();
    }

    async editRestoran() {
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        }

        await axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }

    async deleteRestoranHandler(restoranId) {
        await axios.delete("/restoran/" + restoranId);
        await this.loadRestorans();
    }

    loadingHandler = () => {
        const currentIsLoading = this.state.isLoading;
        this.setState({isLoading: !currentIsLoading});
        console.log(this.state.isLoading);
    };

    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await axios.get("/restorans");
        for (let key in response.data) {
            fetchedRestorans.push({
                ...response.data[key]
            });
        }
        this.setState({
            restorans: fetchedRestorans
        });
    };

    addRestoranHandler = () => {
        this.setState({isCreate: true});
    };

    canceledHandler = () => {
        this.setState({isCreate: false, isEdit: false});
    };

    clearForm = () => {
        this.setState({
            nama: "",
            nomorTelepon: "",
            alamat: "",
            rating: ""
        });
    };
}

export default Restorans;
