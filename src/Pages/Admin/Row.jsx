import React, { useState } from "react"
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import { Box, Button, Collapse, IconButton, Table, TableBody, TableCell, TableHead, TableRow, Typography } from "@mui/material";
import VisibilityIcon from "@mui/icons-material/Visibility";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import CancelIcon from "@mui/icons-material/Cancel";

const Row = (props) => {
    const { row, approve, reject } = props;
    const [open, setOpen] = useState(false);

    return (
      <React.Fragment>
        <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
          <TableCell>
            <IconButton
              aria-label="expand row"
              size="small"
              onClick={() => setOpen(!open)}
            >
              {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
            </IconButton>
          </TableCell>
          <TableCell component="th" scope="row">
            {row.driveApplication.student.user.id}
          </TableCell>
          <TableCell align="left">{row.driveApplication.student.user.firstName}</TableCell>
          <TableCell align="">{row.driveApplication.student.user.lastName}</TableCell>
          <TableCell >
                <VisibilityIcon />
            </TableCell>
          <TableCell align="">{row.driveApplication.placementDrive.companyName}</TableCell>
          <TableCell >
                <VisibilityIcon />       
            </TableCell>
            <TableCell>
            <Button variant="contained" startIcon={<CheckCircleIcon />} sx={{ backgroundColor: "#3C0753", marginRight: 2, ":hover": { bgcolor: "#030637" } }}
                            onClick={() => {
                                approve(row.driveApplication.id)
                            }}>Approve</Button>
                        <Button variant="contained" startIcon={<CancelIcon />} sx={{ backgroundColor: "#D0312D", marginX: 2, ":hover": { bgcolor: "#990F02" } }} 
                        onClick={() => {
                            reject(row.driveApplication.id)
                        }}>Reject</Button>
            </TableCell>
        </TableRow>
        <TableRow>
          <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <Box sx={{ margin: 1 }}>
                <Typography variant="h6" gutterBottom component="div">
                  Placement History
                </Typography>
                <Table size="small" aria-label="purchases">
                  <TableHead>
                    <TableRow>
                      <TableCell>Company Name</TableCell>
                      <TableCell>Offer Amount</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {
                        row.placedStudents.map((item, index) => (
                            <TableRow key={index}>
                                <TableCell>{item.placementDrive.companyName}</TableCell>
                                <TableCell>{item.offerAmount}</TableCell>
                            </TableRow>
                        ))
                    }
                  </TableBody>
                </Table>
              </Box>
            </Collapse>
          </TableCell>
        </TableRow>
      </React.Fragment>
    );
  }

  export default Row