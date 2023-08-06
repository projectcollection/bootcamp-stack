import { useState, useRef, useEffect } from "react";
import { getCurrentUser } from "../Utils/ApiUtil";
import { baseurl } from "../include/Urlinclude.js";
import { useParams } from "react-router-dom";


const Compare = () => {
    const { id: defaultApplicant } = useParams();

    const [currentId, setCurrentId] = useState(defaultApplicant);
    const [applicant, setApplicant] = useState();
    const [profileJson, setProfileJson] = useState();
    const [resumeObjectUrl, setResumeObjectUrl] = useState();

    useEffect(() => {
        const fetchUser = async () => {
            if (!profileJson) {
                return await getCurrentUser();
            }
        }

        fetchUser().then((data) => {
            setProfileJson(data);
        }).catch((err) => {
            console.log(err)
        });
    }, []);

    useEffect(() => {
        getApplicantData(currentId).then((data) => {
            setApplicant(data)

            const resumeLinkSplit = data.resumeLinks[0].split('/');
            fetchResume(resumeLinkSplit[resumeLinkSplit.length - 1]).then((data) => {
                const resumeUrl = URL.createObjectURL(data);

                setResumeObjectUrl(resumeUrl);
            }).catch(err => {
                console.error(err)
            })
        }).catch((err) => {
            console.error(err)
        })
    }, []);

    const jwtToken = localStorage.getItem("accessToken");
    if (!jwtToken) {
        throw new Error("No access token set.");
    }

    const roles = profileJson?.roles?.map(role => role.role);

    const hasAccess = roles?.some(role => {
        return ["RECRUITER", "SRRECRUITER", "RECRUITERADMIN"].includes(role)
    });

    if (!hasAccess && profileJson) {
        window.location.replace('/profile');
    }

    if (!applicant) {
        return <>
            <h1>
                fetching applicant
            </h1>
        </>
    }

    async function getApplicantData(id) {
        const res = await fetch(`${baseurl}/applicant/applicantById`, {
            method: 'POST',
            body: `${id}`,
            headers: {
                Authorization: 'Bearer ' + jwtToken,
                'Content-Type': 'application/json'
            }
        });

        return await res.json();
    }

    async function fetchResume(fileName) {
        const res = await fetch(`${baseurl}/applicant/downloadFile/${fileName}`, {
            method: 'GET',
            headers: {
                Authorization: 'Bearer ' + jwtToken,
            }
        });

        return await res.blob();
    }

    //TODO: make edit work
    async function editApplicant(applicant) {
        console.log(applicant)
        const res = await fetch(`${baseurl}/applicant/editApplicant`, {
            method: 'POST',
            body: applicant,
            headers: {
                Authorization: 'Bearer ' + jwtToken,
                'Content-Type': 'application/json'
            }
        });
        return await res.json();
    }

    return <>
        <div style={{
            display: 'flex',
            minHeight: '100vh',
            width: '100%',
            flexDirection: 'column',
            justifyContent: 'start',
            alignItems: 'start',
            margin: '2rem 0'
        }}>
            {
                applicant &&

                <div
                    style={{
                        display: 'flex',
                        justifyContent: 'space-around',
                        width: '100%'
                    }}
                >
                    <div>
                        <form>
                            <div
                                style={{
                                    display: 'flex',
                                    flexDirection: 'column'
                                }}
                            >
                                <div
                                    style={{
                                        padding: '5px',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        alignItems: 'start'
                                    }}
                                >
                                    {Object.keys(applicant).map((key) => {
                                        const value = applicant[key];
                                        return <div
                                            key={key}
                                            style={{
                                                display: 'flex',
                                                marginTop: '1rem'
                                            }}
                                        >
                                            <h5 style={{
                                                textAlign: 'left'
                                            }}>
                                                {key}:
                                            </h5>
                                            {
                                                key === 'id' ? <span>{value}</span> :
                                                    Array.isArray(value) ?
                                                        <input value={value || ""} onChange={(event) => {
                                                            let updated = structuredClone(applicant);
                                                            updated[key] = event.target.value.split(',').map(item => item.trim());
                                                            setApplicant(updated)
                                                        }} />
                                                        :
                                                        <input value={value || ""} onChange={(event) => {
                                                            let updated = structuredClone(applicant);
                                                            updated[key] = event.target.value.split(',').map(item => item.trim());
                                                            setApplicant(updated)
                                                        }} />
                                            }
                                        </div>
                                    })}
                                </div>
                            </div>

                        </form>
                        <button onClick={() => {
                            editApplicant(applicant);
                        }}>
                            save
                        </button>
                    </div>

                    <iframe src={resumeObjectUrl} width="500px" height="800px" />
                </div>
            }
        </div>
    </>
}

export default Compare;
