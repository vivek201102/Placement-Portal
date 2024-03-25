import React, {useEffect, useState} from 'react'
import axios from "axios";
import apis from "../../apis.js";
import {toast} from "react-toastify";
import {Box, DialogTitle, TextField, Typography} from "@mui/material";
import {DataGrid} from "@mui/x-data-grid";

const PlacementReport = (props) => {
    // eslint-disable-next-line react/prop-types
    const { placementDrive } = props;
    const token = localStorage.getItem("token");
    const [offers, setOffers] = useState([]);
    const [change, setChange] = useState(false);
    const [applicationCnt, setApplicationCnt] = useState()

    const columns = [
        { field: "student.user.id", headerName: "Student ID", width: 300, valueGetter: (param => param.row.student.user.id) },
        { field: "student.user.firstName", headerName: "First Name", width: 300, valueGetter: (param => param.row.student.user.firstName) },
        { field: "student.user.lastName", headerName: "Last Name", width: 300, valueGetter: (param => param.row.student.user.lastName) },
        { field: "offerAmount", headerName: "Offer", width: 200 }
    ]

    useEffect(() => {
        // fetch placement drives placement offers
        axios.get(`${apis.getOffersByCompany}/${placementDrive.id}`, { headers: { Authorization: token }})
            .then((res) => {
                setOffers(res.data)
            })
            .catch((err) => {
                console.log(err)
                toast.error("server error")
            })

        axios.get(`${apis.getAllDriveApplications}/${placementDrive.id}`, { headers: { Authorization: token }})
            .then((res) => {
                setApplicationCnt(res.data.length)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [change]);
  return (
    <React.Fragment>
        <Box sx={{ display: "flex", alignItems: "center", marginY: 2 }}>
            <Typography variant="h6" sx={{ marginRight: 5}}>No of student applied:</Typography>
            <TextField value={applicationCnt} />
        </Box>
        <Box sx={{ display: "flex", alignItems: "center", marginY: 2 }}>
            <Typography variant="h6" sx={{ marginRight: 5}}>No of student got offer:</Typography>
            <TextField value={offers.length} />
        </Box>
        <Box sx={{ display: "flex", flexDirection:"column", justifyContent: "center", marginY: 2 }}>
            <Typography variant="h6" sx={{ marginRight: 5}}>Offers:</Typography>
            <DataGrid columns={columns} rows={offers} autoHeight />
        </Box>
    </React.Fragment>
  )
}

export default PlacementReport